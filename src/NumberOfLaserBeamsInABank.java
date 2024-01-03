public class NumberOfLaserBeamsInABank {

    public int numberOfBeams(String[] bank) {

        int[] numOfDevices = new int[bank.length];

        for (int i = 0; i < numOfDevices.length; i++) {

            char[] devices = bank[i].toCharArray();

            for (int j = 0; j < devices.length; j++) {
                if (devices[j] == '1') {
                    numOfDevices[i]++;
                }
            }

        }

        int ans = 0;

        for (int i = 0; i < numOfDevices.length; ) {

            if (numOfDevices[i] == 0) {
                // to skip all the empty rows
                i++; //to skip this row do i++
                continue;
            }

            int nextDeviceRow = i + 1;
            while (nextDeviceRow < numOfDevices.length) {
                if (numOfDevices[nextDeviceRow] != 0) {
                    // this row has atleast 1 device
                    break;
                } else {
                    nextDeviceRow++;
                }
            }

            if (nextDeviceRow >= numOfDevices.length) {
                // ie no more security devices left
                break;
            } else {
                ans += (numOfDevices[i] * numOfDevices[nextDeviceRow]);
                i = nextDeviceRow; // to skip all the empty rows
            }

        }

        return ans;

    }

}
