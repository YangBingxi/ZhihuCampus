package edu.njupt.sw.dao;


import edu.njupt.sw.model.Question;
import org.springframework.stereotype.Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

public class MysqlOperation {

    /**
     * @see 插入用户信息
     * @param name
     * @param password
     * @param salt
     * @param headUrl
     * @throws Exception
     */
    public void insertUser(String name, String password, String salt, String headUrl) throws Exception {
        Connection conn;
        Statement stmt ;

        MysqlLink.loadDriver();
        conn = MysqlLink.getConnection();
        stmt = conn.createStatement();
        String sql = "insert into wenda.user (user_name, password, salt, head_url) values (\"" + name + "\",\"" + password + "\",\"" + salt + "\",\"" + headUrl + "\")";
        int i = stmt.executeUpdate(sql);
        if(i>0){
            System.out.println("插入成功");
        }
        else {
            System.out.println("插入失败");
        }
        MysqlLink.release(stmt,conn);
    }

    /**
     * @see 插入问题信息
     * @param name
     * @param password
     * @param salt
     * @param headUrl
     * @throws Exception
     */
    public void insertQuestion(String title, String content, int user_id, Date created_date,int comment_count) throws Exception {
        Connection conn;
        Statement stmt ;

        MysqlLink.loadDriver();
        conn = MysqlLink.getConnection();
        stmt = conn.createStatement();
        String sql = "insert into wenda.question (title, content, user_id, created_date, comment_count) values (\"" + title + "\",\"" + content + "\",\"" + user_id + "\",\"" + created_date + "\",\"" + comment_count + "\")";
        int i = stmt.executeUpdate(sql);
        if(i>0){
            System.out.println("插入成功");
        }
        else {
            System.out.println("插入失败");
        }
        MysqlLink.release(stmt,conn);
    }




//    public List<Question> selectLatestQuestions(int userID, int offset, int limit) throws Exception {
//
//
//        Question question = new Question();
//        Connection conn;
//        Statement stmt;
//        ResultSet rs;
//
//        MysqlLink.loadDriver();
//        conn = MysqlLink.getConnection();
//        String sql = "select from quesion where user_id=userID and between offset and offset+limit";
//        stmt = conn.createStatement();
//        int i = stmt.executeUpdate(sql);
//
//        return question;
//
//    }
}
