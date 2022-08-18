package com.example.demodesignpattern.observer2022.impl;

/**
 * Driver Class
 *
 * @author : zxq
 * @create : 2022/8/18 22:31
 */
public class DriverMain {
    public static void main(String[] args) {
        // create objects for testing
        AverageScoreDisplay averageScoreDisplay = new AverageScoreDisplay();
        CurrentScoreDisplay currentScoreDisplay = new CurrentScoreDisplay();

        // pass the displays to Cricket data
        CricketData cricketData = new CricketData();

        // register display elements
        cricketData.registerObserver(averageScoreDisplay);
        cricketData.registerObserver(currentScoreDisplay);

        // in real app you would have some logic to
        // call this function when data changes
        cricketData.dataChanged();
        //remove an observer
        cricketData.unregisterObserver(averageScoreDisplay);
        // now only currentScoreDisplay gets the
        // notification
        cricketData.dataChanged();
    }
}
