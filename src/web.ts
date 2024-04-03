import { WebPlugin } from '@capacitor/core';
import { Device } from '@capacitor/device';

import type { MyCustomPluginPlugin } from './definitions';

export class MyCustomPluginWeb extends WebPlugin implements MyCustomPluginPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
  async testPlugin(options: { msg: string }): Promise<{ value: string }>{
    console.log('ECHO', options);
    return {value:options.msg};
  }
  async getDeviceUUID(options: { msg: string }): Promise<{ value: string }>{
    if(options.msg == 'android'){
      let deviceID = await Device.getId();
      let deviceDetails = await Device.getInfo();
      let stringData = JSON.stringify({deviceID:deviceID,deviceDetails})
      return {value:stringData}
    }else{
      return {value:"Device not supported"};
    }
  }
  async getHardwareDetails(options: { msg: string }): Promise<{ value: string }>{
    return {value:`Device not supported for ${options.msg}, Only supported devices are android, ios and exe`};
  }
}
