package com.june.pmsys.interceptor;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.resultset.ResultSetHandler;  
import org.apache.ibatis.executor.statement.StatementHandler;  
import org.apache.ibatis.mapping.BoundSql;  
import org.apache.ibatis.mapping.MappedStatement;  
import org.apache.ibatis.plugin.*;  
import org.apache.ibatis.reflection.MetaObject;  
import org.apache.ibatis.reflection.SystemMetaObject;  

import com.june.pmsys.domain.BaseEntity;



import java.sql.*;  

@Intercepts({
     @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class}),  
     @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})
})  
public class PageInterceptor implements Interceptor {  
  
    private static final String SELECT_ID="findbypage";


    //插件运行的代码，它将代替原有的方法
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("PageInterceptor -- intercept");        
        if (invocation.getTarget() instanceof StatementHandler) {  
            StatementHandler statementHandler = (StatementHandler) invocation.getTarget();  
            MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);  
            MappedStatement mappedStatement=(MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
            String selectId=mappedStatement.getId();
            String selectString=selectId.substring(selectId.lastIndexOf("."));
            if(selectString.length()>=SELECT_ID.length()&&SELECT_ID.equals(selectId.substring(selectId.lastIndexOf(".")+1,selectId.lastIndexOf(".")+11).toLowerCase())){
                BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");  
                // 分页参数作为参数对象parameterObject的一个属性  
                String sql = boundSql.getSql();
                BaseEntity page=null;
                Object object=boundSql.getParameterObject();
                
                if (object instanceof BaseEntity){ //只有一个参数的情况  
                    page = (BaseEntity)object;  
                }  
                else if (object instanceof Map){ //多参数的情况，找到第一个Page的参数  
                    for (Map.Entry<String, Object> e : ((Map<String, Object>)object).entrySet()){  
                        if (e.getValue() instanceof BaseEntity){  
                            page = (BaseEntity)e.getValue();  
                            break;  
                        }  
                    }  
                } 
                
                // 重写sql  
                String countSql=concatCountSql(sql);
                String pageSql=concatPageSql(sql,page);
                
                System.out.println("重写的 count  sql        :"+countSql);
                System.out.println("重写的 select sql        :"+pageSql);
                
                Connection connection = (Connection) invocation.getArgs()[0];  
                
                PreparedStatement countStmt = null;  
                ResultSet rs = null;  
                int totalCount = 0;  
                try { 
                    countStmt = connection.prepareStatement(countSql);  
                    rs = countStmt.executeQuery();  
                    if (rs.next()) {  
                        totalCount = rs.getInt(1);  
                    } 
                    
                } catch (SQLException e) {  
                    System.out.println("Ignore this exception"+e);  
                } finally {  
                    try {  
                        rs.close();  
                        countStmt.close();  
                    } catch (SQLException e) {  
                        System.out.println("Ignore this exception"+ e);  
                    }  
                }  
                
                metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);            
              
                //绑定count
                page.setCount(totalCount);
            }
        } 
        
        return invocation.proceed();
    }
    
    /**
     * 拦截类型StatementHandler 
     */
    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {  
            return Plugin.wrap(target, this);  
        } else {  
            return target;  
        }  
    }
    
    @Override
    public void setProperties(Properties properties) {
        
    }  
    
    
    public String concatCountSql(String sql){
        StringBuffer sb=new StringBuffer("select count(*) from ");
        sql=sql.toLowerCase();
        
        if(sql.lastIndexOf("order")>sql.lastIndexOf(")")){
            sb.append(sql.substring(sql.indexOf("from")+4, sql.lastIndexOf("order")));
        }else{
            sb.append(sql.substring(sql.indexOf("from")+4));
        }
        return sb.toString();
    }
    
    public String concatPageSql(String sql,BaseEntity page){
        StringBuffer sb=new StringBuffer();
        sb.append(sql);
        sb.append(" limit ").append(page.getPagebegin()).append(" , ").append(page.getPagesize());
        return sb.toString();
    }
    
    public void setPageCount(){
        
    }
    
}
