package com.lut.test;

import com.lut.service.IBulletinResourceService;
import com.lut.service.IDepartmentService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PageTest {
    @Autowired
    IDepartmentService departmentService;
    @Autowired
    IBulletinResourceService bulletinResourceService;

    @Test
    public void print(){

        bulletinResourceService.getPageDatas(0);
//        departmentService.getPageDatas(1);


    }

}
