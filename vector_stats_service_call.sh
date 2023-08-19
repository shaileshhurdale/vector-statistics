#!/usr/bin/bash

base_url="http://localhost:8080/vector-statistics/"
echo "Please enter vector-id:"
read vector_id
resource_url="${base_url}${vector_id}"

echo "service base url: $base_url"
echo "retrieving mean and standard daviation of vector with id: $vector_id"
echo "calling service with url: $resource_url"

response=$(curl -s "$resource_url")

# Display the response
echo "Vector Statistics: $response"

echo "press any key and hit enter to exit"
read data_input
