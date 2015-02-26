package com.example.android.navigationdrawer;

public class DrawerMenu extends DrawerItem {

    private Integer iconRes;
    private String text;

    public DrawerMenu() {
        super(Type.MENU);
    }

    public Integer getIconRes() {
        return iconRes;
    }

    public DrawerMenu setIconRes(Integer iconRes) {
        this.iconRes = iconRes;
        return this;
    }

    public String getText() {
        return text;
    }

    public DrawerMenu setText(String text) {
        this.text = text;
        return this;
    }
}