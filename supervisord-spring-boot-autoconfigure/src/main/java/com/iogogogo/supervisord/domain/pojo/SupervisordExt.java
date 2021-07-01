//package com.iogogogo.supervisord.domain.pojo;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.io.Serializable;
//
///**
// * Created by tao.zeng on 2021/7/1.
// */
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "supervisord")
//public class SupervisordExt implements Serializable {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @Column(name = "name", unique = true)
//    private String name;
//
//    @Column(name = "`group`")
//    private String group;
//
//    @Column(name = "`remark`", length = 4096)
//    private String remark;
//
//    public SupervisordExt(String name, String group, String remark) {
//        this.name = name;
//        this.group = group;
//        this.remark = remark;
//    }
//}
