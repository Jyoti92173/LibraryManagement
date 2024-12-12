package com.app.librarymanagement.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorDTO {
    private int authorId;
    private String name;
    private String phoneNo;

    public AuthorDTO() {
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }



    @Override
    public String toString() {
        return "AuthorDTO{" +
                "authorId=" + authorId +
                ", name='" + name + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AuthorDTO authorDTO = (AuthorDTO) o;
        return authorId == authorDTO.authorId && Objects.equals(name, authorDTO.name) && Objects.equals(phoneNo, authorDTO.phoneNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId, name, phoneNo);
    }

    public AuthorDTO(int authorId, String name, String phoneNo) {
        this.authorId = authorId;
        this.name = name;
        this.phoneNo = phoneNo;
    }
}
