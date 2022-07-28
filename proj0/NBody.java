public class NBody {

    public static double readRadius(String fileName) {
        In readFile = new In(fileName);
        int skipLine = readFile.readInt();
        double radius = readFile.readDouble();
        return radius;
    }

    public static Body[] readBodies(String fileName) {
        In readFile = new In(fileName);
        int numOfBodies = readFile.readInt();

        double skipLine = readFile.readDouble();

        Body[] planet = new Body[numOfBodies];

        for (int i = 0; i < numOfBodies; i++) {
            double xP = readFile.readDouble();
            double yP = readFile.readDouble();
            double xV = readFile.readDouble();
            double yV = readFile.readDouble();
            double m = readFile.readDouble();
            String img = readFile.readString();
            planet[i] = new Body(xP, yP, xV, yV, m, img);
        }

        return planet;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double universeRadius = NBody.readRadius(filename);
        Body planet[] = readBodies(filename);




        for (int t = 0; t < T; t++) {
            double[] xForces = new double[planet.length];
            double[] yForces = new double[planet.length];

            for (int i = 0; i < planet.length; i++) {
                xForces[i] = planet[i].calcNetForceExertedByY(planet);
                yForces[i] = planet[i].calcNetForceExertedByY(planet);
            }

            for (int i = 0; i < planet.length; i++) {
                planet[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.enableDoubleBuffering();
            StdDraw.setScale(-1 * universeRadius, universeRadius);
            StdDraw.clear();
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Body p : planet) {
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }

    }
}
