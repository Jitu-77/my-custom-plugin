// declare module "@capacitor/core"{
//   interface PluginRegistry{
//     MyCustomPlugin : MyCustomPluginPlugin;
//   }
// }

export interface MyCustomPluginPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  testPlugin(options: { msg: string }): Promise<{ value: string }>;
  getDeviceUUID(options: { msg: string }): Promise<{ value: string }>;
  getHardwareDetails(options: { msg: string }): Promise<{ value: string }>;
}
