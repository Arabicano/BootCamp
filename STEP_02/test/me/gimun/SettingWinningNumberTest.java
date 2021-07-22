package me.gimun;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SettingWinningNumberTest {

    @Test
    void winningNumSet() {
        SettingWinningNumber settingWinningNumber = new SettingWinningNumber();
        int[] nums = new int[6];
        nums[0] = 3;
        nums[1] = 10;
        nums[2] = 13;
        nums[3] = 21;
        nums[4] = 26;
        nums[5] = 31;

        settingWinningNumber.winningNumSet(nums,40);

        assertEquals(settingWinningNumber.getWinningLotto().get(0),3);
        assertEquals(settingWinningNumber.getWinningLotto().get(5),31);
        assertEquals(settingWinningNumber.getWinningBonusNum(),40);


        System.out.println("winningLotto : " + settingWinningNumber.getWinningLotto());
        System.out.println("winningBonus : " + settingWinningNumber.getWinningBonusNum());
    }
}