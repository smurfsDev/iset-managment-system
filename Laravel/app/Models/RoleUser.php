<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Relations\Pivot;

class RoleUser extends Pivot
{
    public function class(){
        return $this->hasOne(Classe::class,'id','classe');
    }

    public function user(){
        return $this->hasOne(User::class,'id','user_id');
    }
}
