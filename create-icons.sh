#!/bin/bash

# Create directory for all icons
mkdir -p icons

# Source PNG file
SOURCE_ICON="src/main/resources/images/HomeScreen/icon.png"

# macOS .icns
echo "Creating macOS icon..."
mkdir -p icon.iconset

# Use magick instead of convert
magick "$SOURCE_ICON" -resize 16x16     icon.iconset/icon_16x16.png
magick "$SOURCE_ICON" -resize 32x32     icon.iconset/icon_16x16@2x.png
magick "$SOURCE_ICON" -resize 32x32     icon.iconset/icon_32x32.png
magick "$SOURCE_ICON" -resize 64x64     icon.iconset/icon_32x32@2x.png
magick "$SOURCE_ICON" -resize 128x128   icon.iconset/icon_128x128.png
magick "$SOURCE_ICON" -resize 256x256   icon.iconset/icon_128x128@2x.png
magick "$SOURCE_ICON" -resize 256x256   icon.iconset/icon_256x256.png
magick "$SOURCE_ICON" -resize 512x512   icon.iconset/icon_256x256@2x.png
magick "$SOURCE_ICON" -resize 512x512   icon.iconset/icon_512x512.png
magick "$SOURCE_ICON" -resize 1024x1024 icon.iconset/icon_512x512@2x.png

# Create icns (with error checking)
if iconutil -c icns icon.iconset; then
    mv icon.icns icons/icon.icns
else
    echo "Failed to create ICNS file. Trying alternative method..."
    # Alternative method using magick
    magick "$SOURCE_ICON" -resize 1024x1024 icons/icon.icns
fi

rm -rf icon.iconset

# Windows .ico
echo "Creating Windows icon..."
magick "$SOURCE_ICON" -define icon:auto-resize=256,128,64,48,32,16 icons/icon.ico

# Linux .png
echo "Creating Linux icon..."
magick "$SOURCE_ICON" -resize 256x256 icons/icon.png

echo "Icons created successfully!"
