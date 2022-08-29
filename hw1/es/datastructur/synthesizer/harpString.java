package es.datastructur.synthesizer;

public class harpString {
    private static final int SR = 44100;
    private static final double DECAY = .996;
    private BoundedQueue<Double> buffer;

    public harpString(double frequency) {
        buffer = new ArrayRingBuffer<>((int) (Math.round(SR / frequency)));
        while (!buffer.isFull()) {
            buffer.enqueue(0.0);
        }
    }

    public void pluck() {
        // TODO: Dequeue everything in buffer, and replace with random numbers
        //       between -0.5 and 0.5. You can get such a number by using:
        //       double r = Math.random() - 0.5;
        //
        //       Make sure that your random numbers are different from each
        //       other.
        while (!buffer.isEmpty()) {
            buffer.dequeue();
        }
        double[] randArr = new double[buffer.capacity()];
        for (int i = 0; i < randArr.length; i++) {
            randArr[i] = Math.random() - 0.5;//TODO
        }
        for (double r : randArr) {
            buffer.enqueue(r);
        }
    }

    public void tic() {
        // Dequeue the front sample and enqueue a new sample that is
        //       the average of the two multiplied by the DECAY factor.
        //       Do not call StdAudio.play().
        double newSample = DECAY * (buffer.dequeue() + buffer.peek()) / 2;
        newSample = -1 * newSample;// Flipping the sign will produces harp-like sound.
        buffer.enqueue(newSample);
    }

    public double sample() {
        // Return the correct thing.
        if (buffer.peek() == null) {
            try {
                throw new Exception("buffer.peek() is null!");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return buffer.peek();
    }

}
