
import java.util.*;
import java.io.*;

public class MilkMeasurement {

    // 0 means bessie
    // 1 --> elsie
    // 2 --> midlred

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("measurement.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("measurement.out"));

        int bRate = 7;
        int eRate = 7;
        int mRate = 7;
        int switches = 0;

        ArrayList<Integer> pLeader = new ArrayList<Integer>();
        pLeader.add(0);
        pLeader.add(1);
        pLeader.add(2);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        String[] logs = new String[n];
        for (int i = 0; i < n; i++) {
            logs[i] = (br.readLine());
        }
        Arrays.sort(logs);


        int date = 0;
        for (int i = 0; i < 100; i ++) {
            if (date < n && i == Integer.parseInt(logs[date].substring(0, 1))) {
                switch (logs[date].substring(2, 3)) {
                    case "B":
                        bRate += Integer.parseInt(logs[date].substring(logs[date].length() - 2));
                        date++;
                        break;
                    case "E":
                        eRate += Integer.parseInt(logs[date].substring(logs[date].length() - 2));
                        date++;
                        break;
                    case "M":
                        mRate += Integer.parseInt(logs[date].substring(logs[date].length() - 2));
                        date++;
                        break;
                }

            }

            int[] container = {bRate, eRate, mRate};
            ArrayList<Integer> temp = new ArrayList<>();
            temp = (ArrayList<Integer>) pLeader.clone();
            updateLeaderBoard(container, pLeader);

            if (!temp.equals(pLeader)) {
                switches++;
                temp = pLeader;
            }
        }

        pw.println(switches);

        br.close();
        pw.close();
    }

    public static ArrayList<Integer> updateLeaderBoard(int[] bem, ArrayList<Integer> pLeader) {
        int largest = bem[0];
        for (int i = 0; i < bem.length; i++) {
            if (bem[i] > largest) largest = bem[i];
        }
        pLeader.clear();
        for (int i = 0; i < bem.length; i++) {
            if (bem[i] == largest) pLeader.add(i);
        }

        return pLeader;
    }
}
