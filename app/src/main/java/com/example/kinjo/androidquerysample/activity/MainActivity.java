package com.example.kinjo.androidquerysample.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.kinjo.androidquerysample.R;
import com.example.kinjo.androidquerysample.activity.common.SampleActivity;
import com.example.kinjo.androidquerysample.dao.EmployeeDao;
import com.example.kinjo.androidquerysample.database.DatabaseHelper;
import com.example.kinjo.androidquerysample.entity.Employee;

import java.sql.SQLException;


/**
 * MainActivity
 */
public class MainActivity extends SampleActivity {

    private EmployeeDao mDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* 社員DAO取得 */
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(this);
            mDao = helper.getEmployeeDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /* 登録ボタン */
        q.id(R.id.employee_register_button).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String code = q.id(R.id.employee_code).getText().toString();
                final String name = q.id(R.id.employee_name).getText().toString();
                Employee employee = new Employee();
                employee.setCode(code);
                employee.setName(name);
                try {
                    mDao.create(employee);
                    registerSuccess();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        /* 検索ボタン */
        q.id(R.id.employee_search_button).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String code = q.id(R.id.employee_code_search).getText().toString();
                try {
                    final Employee employee = mDao.findByCode(code);
                    if(employee != null) {
                        q.id(R.id.employee_search_result).text(employee.getName());
                    } else {
                        q.id(R.id.employee_search_result).text(R.string.not_found);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    /**
     * 登録成功時
     */
    private void registerSuccess() {
        Toast.makeText(MainActivity.this, R.string.register_success, Toast.LENGTH_SHORT).show();
        q.id(R.id.employee_code).clear();
        q.id(R.id.employee_name).clear();
    }
}
