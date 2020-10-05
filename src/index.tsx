import { NativeModules } from 'react-native';

interface NativeVariantsType {
  getString(key: String, defaultValue: String): Promise<string>;
  getColor(key: String, defaultValue: String): Promise<string>;
}

const { NativeVariants } = NativeModules;

export default NativeVariants as NativeVariantsType;
