.class public Demo
.super java/lang/Object
.method public <init>()V
   aload_0
   invokenonvirtual java/lang/Object/<init>()V
   return
.end method
.method public static main([Ljava/lang/String;)V
 .limit locals 10
 .limit stack 256
 getstatic java/lang/System/out Ljava/io/PrintStream;
L1:
new frame_0
dup
invokespecial frame_0/<init>()V
dup
aload 0
putfield frame_0/sl Ljava/lang/Object;
astore 0
aload 0
sipush 5
newarray int
putfield frame_0/loc_0 [I
aload 0
getfield frame_0/loc_0 [I
sipush 2
sipush 3
iastore
aload 0
getfield frame_0/loc_0 [I
sipush 2
iaload
i2d
ldc2_w 3.2
dadd
aload 0
getfield frame_0/sl Ljava/lang/Object;
astore 0
return
.end method
