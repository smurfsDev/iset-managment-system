<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

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
        "status"
    ];
    public function responsableClub(){
        return $this->belongsTo(User::class,'id');
    }

    public function administrateur(){
        return $this->belongsTo(User::class,'id');
    }
}
