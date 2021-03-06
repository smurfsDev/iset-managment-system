<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class DemandeAdhesionClub extends Model
{
    use HasFactory;
    protected $table='demande_adhesion_club';
    protected $fillable = [
        "user_id",
        "club_id",
        "admin_id",
        "message",
        "status"
    ];

    public function club(){
        return $this->belongsTo('App\Models\club');
    }

    public function user(){
        return $this->belongsTo('App\Models\User');
    }


}
