GlassFaceDetection

Soon to be GlassFaceRecognition

TODOs: Test functionality, better UI, more documentation, better naming scheme for trained faces, save trained faces

Cool features:
1) actually frees the camera. OpenCV documentation does not cover this.
2) CVLibTools -> converts OpenCV mats to JavaCV mats, still need testing
3) Merges OpenCV and JavaCV together


Usage: run app

Face Detection + Recognition Mode: on by default
- swipe left/right to change minimum face size (higher the faster)
- tap to exit
- long tap to switch to face training mode

Face Training mode:
- same as face detection
- short tap to save current frame and go to face selection mode
- must select eight faces
- long tap to exit/cancel

Face Selection mode:
- a static image with face rectangles
- swipe left/right to highlight the face you want to recognize
- short tap to remember selected rectangle as a face