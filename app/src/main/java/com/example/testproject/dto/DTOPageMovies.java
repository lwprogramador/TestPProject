package com.example.testproject.dto;

import java.util.List;

public class DTOPageMovies {
    private int pages;
    private List<DTOMovies> results;
    private int status;
    private String message;

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<DTOMovies> getResult() {
        return results;
    }

    public void setResult(List<DTOMovies> result) {
        this.results = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
