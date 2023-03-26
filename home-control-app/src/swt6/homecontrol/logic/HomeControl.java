package swt6.homecontrol.logic;

import swt6.control.aircondition.AirConditionApi;
import swt6.control.aircondition.AirConditionApiFactory;
import swt6.modular.beans.Timer;
import swt6.modular.beans.TimerFactory;
import swt6.powerplant.inverter.InverterApi;
import swt6.powerplant.inverter.InverterApiFactory;

import java.util.ArrayDeque;
import java.util.Deque;

public class HomeControl {
  private static int nrOfTemperatures = 10;
  private static int nrOfCurrents = 10;
  private static Double warmestTemp = 24.0;
  private static Double coldestTemp = 22.0;
  private static Double minCurrentForAircon = 0.1;
  private static int timerInterval = 5000;
  private static int numberOfTicks = 10000;

  private static Deque<Double> pastTemperatures = new ArrayDeque<>(nrOfTemperatures+1);
  private static Deque<Double> pastCurrents = new ArrayDeque<>(nrOfCurrents+1);
  private static InverterApi inverter = InverterApiFactory.createInverterApi();
  private static AirConditionApi aircon = AirConditionApiFactory.createAirConditionApi();

  public static void main(String args[]){
    // create Timer
    Timer timer = TimerFactory.createTimer(timerInterval, numberOfTicks);
    // register Event
    timer.addTimerListener(event -> handleTimerEvent());
    // start timer
    timer.start();
  }

  public static void handleTimerEvent(){
    // add new values
    pastTemperatures.addFirst(aircon.getRoomTemperature());
    pastCurrents.addFirst(inverter.getActualCurrent());

    // remove old values
    if (pastTemperatures.size() > nrOfTemperatures)
      pastTemperatures.removeLast();
    if (pastCurrents.size() > nrOfTemperatures)
      pastCurrents.removeLast();

    printDeques();

    if ((pastTemperatures.size() < nrOfTemperatures) || (pastCurrents.size() < nrOfCurrents))
      return; //nothing to do, not enough values yet

    // check average temperature
    Double averageTemp = averageTemperature();

    if (averageTemp < coldestTemp)
      tooCold();

    if (averageTemp > warmestTemp)
      tooWarm();
  }



  private static void tooWarm() {
    Double averageCurrent = averageCurrent();
    if (averageCurrent < minCurrentForAircon){
      System.out.println("==PV Power not sufficient==");
      return;

    }

    if(aircon.isOn())
      return;

    aircon.turnOn();
  }

  private static void tooCold() {
    if (!aircon.isOn())
      return;
    aircon.turnOff();
  }

  private static void printDeques() {
    System.out.println("==========================");
    System.out.print("Past Temperatures: ");
    System.out.println(pastTemperatures);
    System.out.print("Past Currents: ");
    System.out.println(pastCurrents);
    System.out.println("==========================");
    System.out.println();
    System.out.println();
  }

  public static Double average(Deque<Double> deque){
    Double sum = 0.0;
    for (Double d:deque) {
      sum += d;
    }
    return sum / deque.size();
  }

  private static Double averageTemperature() {
    Double averageTemp = average(pastTemperatures);
    System.out.print("Average Temperature: ");
    System.out.printf("%.2f",averageTemp);
    System.out.println();
    return averageTemp;
  }

  private static Double averageCurrent() {
    Double averageCurrent = average(pastCurrents);
    System.out.print("Average Current: ");
    System.out.printf("%.2f",averageCurrent);
    System.out.println();
    return averageCurrent;
  }
}

