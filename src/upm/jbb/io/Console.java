package upm.jbb.io;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Console extends ByteArrayOutputStream {
	ConsoleListener observer;
	PrintStream standar;

	public Console(ConsoleListener observer) {
		this.observer = observer;
		this.standar = System.out;
		System.setOut(new PrintStream(this, true));
	}

	public ConsoleListener getObserver() {
		return this.observer;
	}

	public void setObserver(ConsoleListener observer) {
		this.observer = observer;
	}

	public void open() {
		System.setOut(new PrintStream(this, true));
	}

	@Override
	public synchronized void reset() {
		super.reset();
		if (this.observer != null) this.observer.update(this.toString());
	}

	@Override
	public void close() {
		System.setOut(standar);
	}

	@Override
	public synchronized void write(byte[] b, int off, int len) {
		super.write(b, off, len);
		if (this.observer != null) this.observer.update(this.toString());
	}

	@Override
	public synchronized void write(int b) {
		super.write(b);
		if (this.observer != null) this.observer.update(this.toString());
	}

}
