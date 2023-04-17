<?php

function x() {
    return array_map(function () {
        return [
            'foo' => 1,
            'bar' => 2,
        ];
    }, []);
}
