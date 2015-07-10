package com.example.kinjo.androidquerysample.dao;

import com.example.kinjo.androidquerysample.entity.Employee;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;

/**
 * 社員マスタ用Dao
 * Created by kinjo on 2015/07/10.
 */
public class EmployeeDao {
    private Dao<Employee, Integer> mEmployeeDao;

    public EmployeeDao(Dao<Employee, Integer> dao) {
        this.mEmployeeDao = dao;
    }

    /**
     * コードから社員情報を取得する
     * @param code 社員コード
     * @return 社員情報
     * @throws SQLException
     */
    public Employee findByCode(String code) throws SQLException {
        /* 条件を指定するにはQueryBuilderを生成し、QueryBuilderからWhereインスタンスを取得します. */
        QueryBuilder<Employee, Integer> qb =  mEmployeeDao.queryBuilder();
        Where<Employee, Integer> where = qb.where();
        where.eq(Employee.CODE, code);
        // 複数条件を指定したい場合はwhere.and() で繋げられるよ
        return mEmployeeDao.queryForFirst(where.prepare());
    }

    /**
     * 社員を登録する
     * @param employee 社員情報
     * @return 登録時のステータス
     * @throws SQLException
     */
    public int create(Employee employee) throws SQLException {
        return mEmployeeDao.create(employee);
    }
}
