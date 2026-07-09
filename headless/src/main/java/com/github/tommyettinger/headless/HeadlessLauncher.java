package com.github.tommyettinger.headless;

import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.github.tommyettinger.TransparencyProcessor;
import picocli.CommandLine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "fnt-processor", version = "fnt-processor 0.0.1",
		description = "Does TextraTypist's preferred modifications to AngelCode BMFont .fnt and .png files.",
		mixinStandardHelpOptions = true)
public class HeadlessLauncher implements Callable<Integer> {

	@CommandLine.Parameters(description = "One or more paths to .fnt files with .png files next to them in their folders.")
	public List<String> input = new ArrayList<>();

	public static void main(String[] args) {
		int exitCode = new picocli.CommandLine(new HeadlessLauncher()).execute(args);
		System.exit(exitCode);
	}

	@Override
	public Integer call() {
		HeadlessApplicationConfiguration configuration = new HeadlessApplicationConfiguration();
		configuration.updatesPerSecond = -1;
		new HeadlessApplication(new TransparencyProcessor(input, false), configuration) {
			{
				try {
					mainLoopThread.join();
				} catch (InterruptedException e) {
					System.out.println("Interrupted!");
				}
			}
		};
		return 0;
	}
}