package psy.fit.bstu.thirdlab;

import java.util.Date;

public class Note {
    private String text;
    private Date date;

    Note(String text, Date date) {
        this.text = text;
        this.date = date;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
}
