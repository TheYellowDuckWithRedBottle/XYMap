package com.levi.xymap.entity;

import javax.print.Doc;

/**
 * @ClassName
 * @Description TODO
 * @Author LeviFan
 * @Date 2023/2/19 22:35
 * @Version 1.0
 **/
public class Document {
    public static final String DOTS=".";

    public static enum Type {
        doc,docx,xls,xlsx,txt,xml,zip,cpg,dbf,shp,shx,prj,sbx,sbn,fix,qix;
        public static final Type getType(String value){
            for (Type item:values()){
                if (item.name().equals(value)) return item;
            }
            return null;
        }
    }
    private String name;
    private byte[] content;
    private Type type;

    public String getName() {
        return name;
    }

    public byte[] getContent() {
        return content;
    }

    public Type getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Document(String name, Type type) {
        this.name = name;
        this.type = type;
    }
    public Document(String name, byte[] content, Type type) {
        this.name = name;
        this.content = content;
        this.type = type;
    }
    public static Document getDocByName(String fileName){
        if(fileName.contains(DOTS)){
            String[] names=fileName.split("\\"+DOTS);
            return new Document(fileName,Type.getType(names[names.length-1]));
        }else {
            return new Document(fileName,Type.getType("txt"));
        }
    }
}
