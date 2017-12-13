package concurrent.patterns.builder1;

/**
 * Created by niewj on 2017/10/19.
 */
class Director {
    private Builder builder;

    Director(Builder builder){
        this.builder = builder;
    }

    Product construct(){
        builder.buildTitle("title-1");
        builder.buildContent("this is message content");
        return builder.getProduct();
    }
}
