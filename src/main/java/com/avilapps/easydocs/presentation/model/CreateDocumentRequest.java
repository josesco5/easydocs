package com.avilapps.easydocs.presentation.model;

import org.springframework.web.multipart.MultipartFile;

public class CreateDocumentRequest {
    private String subject;
    private Integer folio;
    private MultipartFile file;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getFolio() {
        return folio;
    }

    public void setFolio(Integer folio) {
        this.folio = folio;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
