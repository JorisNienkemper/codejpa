package com.infosupport.demo30startwithrelations.app.domain.manytomany.bidirectional.builder;


public class TitleBuilder {

    private String name; //represents name of title

    public TitleBuilder withName(String name){
        this.name = name;
        return this;
    }
    public static TitleBuilder addTitle(){
        return new TitleBuilder();
    }

    public TitleAuthorListBuilder addAuthors(){
        return new TitleAuthorListBuilder();
    }
    public static class TitleAuthorListBuilder {

    }
}
