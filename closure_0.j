.class public closure_0
.super java/lang/Object
.implements fun_int_bool_int
.field public sl Lframe_0;
.method public <init>()V
aload_0
invokenonvirtual java/lang/Object/<init>()V
return
.end method

.method public apply(IZ)I
.limit locals 7
.limit stack 256
new frame_1
dup
invokespecial frame_1/<init>()V
dup
aload 0
getfield closure_0/sl Lframe_0;
putfield frame_1/sl Lframe_0;
dup
iload 1
putfield frame_1/loc_0 I
dup
iload 2
putfield frame_1/loc_1 Z
astore 0
L1:
aload 0
getfield frame_1/loc_0 I
sipush 2
imul
ireturn
.end method