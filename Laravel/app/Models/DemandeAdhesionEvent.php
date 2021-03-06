<?php

namespace App\Models;

use App\Models\User;
use App\Models\DemandeEvent;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Factories\HasFactory;

class DemandeAdhesionEvent extends Model
{
    use HasFactory;
    protected $table='demande_adhesion_events';
    protected $fillable = [
        "idStudent",
        "idEvent",
        "status"
    ];
    public function student(){
        return $this->belongsTo(User::class,'idStudent','id');
    }

    // public function event(){
    //     return $this->belongsTo(DemandeEvent::class,'idEvent');
    // }
    public function demandeEvent(){
        return $this->hasOne('App\Models\DemandeEvent','id','idEvent');
    }


}
