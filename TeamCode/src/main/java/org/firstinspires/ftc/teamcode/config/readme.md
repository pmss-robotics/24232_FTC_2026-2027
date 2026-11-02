# Sample XML Configuration

Steps to write a configuration on Android Studio:

1. Fill in the full configuration using the guide
   https://ftc9929.com/2019/12/16/stress-free-ftc-hardware-configurations/
   https://xml.davisluxenberg.com/
2. Use REV Hardware Client to find the Control Hub's parent module address (usually 173)
3. Find the Expansion Hub's address
4. Move the file to [/res/xml](../../../../../../res/xml)
5. Do a full build on the Control Hub (TeamCode not Sloth Load)