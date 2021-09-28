package com.pku.ss.movienftserver.dao;

import com.pku.ss.movienftserver.data.MovieTicket;

import java.util.List;

public interface MovieTicketStorage {

    int addMovieTicket(MovieTicket movieTicket);

    List<MovieTicket> getMovieTicketByMovieId(int movieId);
}
