package com.app.librarymanagement.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PublisherDTO {
    private int publisherId;
    private String name;

    public PublisherDTO() {
    }

    public PublisherDTO(int publisherId, String name) {
        this.publisherId = publisherId;
        this.name = name;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PublisherDTO that = (PublisherDTO) o;
        return publisherId == that.publisherId && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(publisherId, name);
    }

    @Override
    public String toString() {
        return "PublisherDTO{" +
                "publisherId=" + publisherId +
                ", name='" + name + '\'' +
                '}';
    }
}
