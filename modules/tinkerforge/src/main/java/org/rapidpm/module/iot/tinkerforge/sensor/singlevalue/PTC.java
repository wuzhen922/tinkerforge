/*
 * Copyright [2014] [www.rapidpm.org / Sven Ruppert (sven.ruppert@rapidpm.org)]
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.rapidpm.module.iot.tinkerforge.sensor.singlevalue;

import com.tinkerforge.BrickletPTC;
import com.tinkerforge.IPConnection;
import com.tinkerforge.NotConnectedException;
import com.tinkerforge.TimeoutException;

/**
 * Created by Sven Ruppert on 22.02.14.
 */
public class PTC extends TinkerForgeSensorSingleValue<BrickletPTC> {


    public PTC(String UID, int callbackPeriod, IPConnection ipcon) {
        super(UID, callbackPeriod, ipcon);
    }

    @Override
  protected double convertRawValue(int sensorRawValue) {
    return sensorRawValue / 100.0;
  }

  @Override
  public void initBricklet() {
    try {
      bricklet.setResistanceCallbackPeriod(callbackPeriod);
      bricklet.setTemperatureCallbackPeriod(callbackPeriod);
      bricklet.addTemperatureListener(this::execute);
    } catch (TimeoutException | NotConnectedException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void connectBricklet() {
    bricklet = new BrickletPTC(UID, ipcon);
  }
}
