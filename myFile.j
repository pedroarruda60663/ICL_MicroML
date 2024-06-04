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
new ref_double
dup
invokespecial ref_double/<init>()V
dup
ldc2_w 2.2
putfield ref_double/value D
putfield frame_0/loc_0 Lref_double;
aload 0
getfield frame_0/loc_0 Lref_double;
aload 0
getfield frame_0/loc_0 Lref_double;
getfield ref_double/value D
ldc2_w 5.7
dadd
putfield ref_double/value D
aload 0
getfield frame_0/loc_0 Lref_double;
getfield ref_double/value D
invokevirtual java/io/PrintStream/println(D)V
aload 0
getfield frame_0/sl Ljava/lang/Object;
astore 0
return
.end method
