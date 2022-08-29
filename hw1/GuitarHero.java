import es.datastructur.synthesizer.GuitarString;

public class GuitarHero {
    private static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private static double[] concert = new double[keyboard.length()];

    public static void main(String[] args) {

        GuitarString[] gString = new GuitarString[keyboard.length()];
        for (int i = 0; i < keyboard.length(); i++) {
            concert[i] = 440 * Math.pow(2, (double) (i - 24) / 12);
            gString[i] = new GuitarString(concert[i]);
        }

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (keyboard.indexOf(key) < 0) {
                    continue;
                }
                gString[keyboard.indexOf(key)].pluck();
            }
            double totalSample = 0;
            for (GuitarString guitarString : gString) {
                totalSample += guitarString.sample();
            }
            double sample = totalSample / gString.length;
            StdAudio.play(sample);
            for (GuitarString guitarString : gString) {
                guitarString.tic();
            }

        }

    }
}
