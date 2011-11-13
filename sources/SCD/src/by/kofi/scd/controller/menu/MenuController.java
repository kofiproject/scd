package by.kofi.scd.controller.menu;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Client menu state keeper
 *
 * @author harchevnikov_m
 *         Date: 07/11/11
 *         Time: 21:36
 */
@Service
@Scope("session")
public class MenuController {
    private String activeMenuItem = "id1";

    public String getActiveMenuItem() {
        return activeMenuItem;
    }

    public void setActiveMenuItem(String activeMenuItem) {
        this.activeMenuItem = activeMenuItem;
    }
}
