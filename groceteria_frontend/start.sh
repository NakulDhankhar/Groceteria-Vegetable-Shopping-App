#!/bin/bash

echo "Starting Groceteria Frontend Application..."
echo ""
echo "Make sure the backend is running on http://localhost:8080"
echo "Frontend will be available at http://localhost:4200"
echo ""
echo "Installing dependencies..."
npm install
echo ""
echo "Starting Angular development server..."
ng serve --open 