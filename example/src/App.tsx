import * as React from 'react';
import {StyleSheet, Text, View, Image} from 'react-native';
import NativeVariants from 'native-variants';

interface ImagesTypes {
  uri: "pikachu_dab" | "pokemon2"
}

export default function App() {
  const [result, setResult] = React.useState<string | undefined>();
  const [color, setColor] = React.useState<string>("#000");

  React.useEffect(() => {
    async function getAppNameAndColor() {
      let appName = await NativeVariants.getString("app_name", "")
      setResult(appName)
      let primaryColor = await NativeVariants.getColor("colorPrimary", color)
      console.log(primaryColor);
      setColor(primaryColor)
    }
    getAppNameAndColor()
  }, []);

  return (
    <View style={styles.container}>
      <Text style={{color: color}}>Result: {result}</Text>
      <Image source={{uri: "pikachu_dab"} as ImagesTypes} width={100} height={100} style={{width: 100, height: 100}}/>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
});
