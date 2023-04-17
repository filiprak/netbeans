echo "Starting unit tests...";

for file_input in test/test[0-9][0-9].php; do
    test_name=$(basename "$file_input" .php);
    file_expected="test/$test_name.formatted.php"
    
    echo "Testing: $file_input $filename";
    
    java -jar build/bin/nb-fmt.jar $file_input -o test/out.test.php

    if ! diff --color $file_expected test/out.test.php; then
        echo "FAILED!";
        exit 5;
    fi
    echo "OK! All tests passed.";
done
