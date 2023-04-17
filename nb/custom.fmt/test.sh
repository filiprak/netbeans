echo "Starting unit tests...";

RED='\033[41m'         # Red
GREEN='\033[42m'       # Green
CL='\033[0m'

for file_input in test/test[0-9][0-9].php; do
    test_name=$(basename "$file_input" .php);
    file_expected="test/$test_name.formatted.php"
    
    echo "Testing: $file_input $filename";

    rm -rf test/out.test.php;
    touch test/out.test.php;
    java -jar build/bin/nb-fmt.jar $file_input -o test/out.test.php;

    if ! diff --color --strip-trailing-cr $file_expected test/out.test.php; then
        echo -e "${RED}TEST '$file_input' FAILED!${CL}";
        exit 5;
    fi
done
echo -e "${GREEN}OK! All tests passed.${CL}";
