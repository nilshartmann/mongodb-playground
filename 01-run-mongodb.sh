#! /bin/bash

base_dir=$PWD/$(dirname $0)

$base_dir/mongodb/bin/mongod --dbpath $base_dir/data