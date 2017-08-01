package com.mafh.bean;

/**
 * @author mafh
 * @create 2017-08-01 16:52
 * Created With Intellij IDEA
 * 与消息表对应的实体类
 */
public class Message {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 命令
     */
    private String command;
    /**
     * 简介
     */
    private String description;
    /**
     * 详细内容
     */
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
