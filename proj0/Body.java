public class Body {
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;

    private static final double G = 6.67e-11;

    public Body(double xP, double yP, double xV, double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Body(Body b) {
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b) {
        double Distance;
        Distance =
                Math.sqrt(
                        (this.xxPos - b.xxPos) * (this.xxPos - b.xxPos)
                                + (this.yyPos - b.yyPos) * (this.yyPos - b.yyPos));
        return Distance;
    }

    public double calcForceExertedBy(Body b) {
        double force;
        if (this.equals(b)) {
            force = 0;
        } else {
            force = G * this.mass * b.mass / (this.calcDistance(b) * this.calcDistance(b));
        }
        return force;
    }

    public double calcForceExertedByX(Body b) {
        double forceX;
        if (this.equals(b)) {
            forceX = 0;
        } else {
            forceX = this.calcForceExertedBy(b) * (b.xxPos - this.xxPos) / this.calcDistance(b);
        }
        return forceX;
    }

    public double calcForceExertedByY(Body b) {
        double forceY;
        if (this.equals(b)) {
            forceY = 0;
        } else {
            forceY = this.calcForceExertedBy(b) * (b.yyPos - this.yyPos) / this.calcDistance(b);
        }
        return forceY;
    }

    public double calcNetForceExertedByX(Body[] bodies) {
        double netForceX = 0;
        for (Body b : bodies) {
            netForceX += this.calcForceExertedByX(b);
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Body[] bodies) {
        double netForceY = 0;
        for (Body b : bodies) {
            netForceY += this.calcForceExertedByY(b);
        }
        return netForceY;
    }

    /**
     * Update the position and velocity of this body
     *
     * @param dt the interval of update
     * @param fX force component of x
     * @param fY force component of y
     */
    public void update(double dt, double fX, double fY) {
        this.xxVel += dt * fX / this.mass;
        this.yyVel += dt * fY / this.mass;

        this.xxPos += dt * this.xxVel;
        this.yyPos += dt * this.yyVel;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }
}
