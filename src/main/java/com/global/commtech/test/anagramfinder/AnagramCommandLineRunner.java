package com.global.commtech.test.anagramfinder;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AnagramCommandLineRunner implements CommandLineRunner {
    private final AnagramGrouper anagramGrouper;
    @Override
    public void run(final String... args) throws Exception {
        Assert.isTrue(args.length == 1, "Please ensure that the input file is provided");

        final File file = new File(args[0]);
        Assert.isTrue(file.exists(), args[0] + " Does not exist");
        Assert.isTrue(file.exists() && file.length()>0, args[0] + " is empty");

        try {
            anagramGrouper.groupAnagrams(args[0]);
            System.out.println("Anagrams grouped successfully.");
        } catch (IOException e) {
            System.err.println("Error processing file: " + e.getMessage());
        }

    }
}
