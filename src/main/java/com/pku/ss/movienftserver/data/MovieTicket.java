package com.pku.ss.movienftserver.data;

import lombok.Data;

@Data
public class MovieTicket {
    int movieTicketId;
    int movieId; //绑定的相应电影ID
    int quantity;//special 类型对应为1 绑定链上唯一地址
    String cover;
    int type;
}
