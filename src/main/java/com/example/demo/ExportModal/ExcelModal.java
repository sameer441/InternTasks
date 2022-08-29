package com.example.demo.ExportModal;

public class ExcelModal {
    String PosHolder;
    String IssuerName;
    String IsIn;
    String NewShortPosition;
    String positionDate;

    public ExcelModal(String posHolder, String issuerName, String isIn, String NewShortPosition, String positionDate) {
        this.PosHolder = posHolder;
        this.IssuerName = issuerName;
        this.IsIn = isIn;
        this.NewShortPosition = NewShortPosition;
        this.positionDate = positionDate;
    }

    public String getNewShortPosition() {
        return NewShortPosition;
    }

    public void setNewShortPosition(String newShortPosition) {
        NewShortPosition = newShortPosition;
    }

    public String getPositionDate() {
        return positionDate;
    }

    public void setPositionDate(String positionDate) {
        this.positionDate = positionDate;
    }

    public String getPosHolder() {
        return PosHolder;
    }

    public void setPosHolder(String posHolder) {
        PosHolder = posHolder;
    }

    public String getIssuerName() {
        return IssuerName;
    }

    public void setIssuerName(String issuerName) {
        IssuerName = issuerName;
    }

    public String getIsIn() {
        return IsIn;
    }

    public void setIsIn(String isIn) {
        IsIn = isIn;
    }
}
