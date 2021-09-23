package com.pku.ss.movienftserver.data;


import lombok.Data;

import java.util.List;

@Data
public class Page<T> {

    int totalNum; //总数
    int pageCount;//页面总数

    int currentPage;//当前页
    int pageSize ;//页大小

    List<T> PageInfo;//页面信息
}
