<?php

namespace App\Models;

use App\Models\DemandeAdhesionEvent;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Factories\HasFactory;

class DemandeEvent extends Model
{
    use HasFactory;
    protected $table='demande_events';
    protected $fillable = [
        "responsableClubId",
        "administrateurId",
        "nomEvent",
        "dateEvent",
        "description",
        "clubId",
        "status"
    ];
    public function responsableClub(){
        return $this->belongsTo(User::class,'responsableClubId');
    }

    public function administrateur(){
        return $this->belongsTo(User::class,'administrateurId');
    }

    public function demandeAdhesionEvent(){
        return $this->hasMany('App\Models\DemandeAdhesionEvent','idEvent');
    }
    public function club(){
        return $this->hasOne('App\Models\club','id','clubId');
    }
}
